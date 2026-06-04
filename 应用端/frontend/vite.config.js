import {
    defineConfig,
    loadEnv
} from 'vite'
import path from 'path'
import uni from '@dcloudio/vite-plugin-uni'
import AutoImport from 'unplugin-auto-import/vite'
import tailwindcss from "tailwindcss";
import banner from 'vite-plugin-banner'
import {
    UnifiedViteWeappTailwindcssPlugin as uvwt
} from 'weapp-tailwindcss/vite';
import autoprefixer from "autoprefixer";

const isH5 = process.env.UNI_PLATFORM === "h5";
const isApp = process.env.UNI_PLATFORM === "app";
const isMp = !isH5 && !isApp;
const WeappTailwindcssDisabled = isH5 || isApp;

// https://vitejs.dev/config/
export default defineConfig(({
    command,
    mode
}) => {
    const timestamp = new Date().getTime()
    const env = loadEnv(mode, process.cwd())
    const isBuild = command === 'build'

    return {
        base: env.VITE_BASE,
        define: {
            'process.env': process.env
        },
        plugins: [
            uni(),
            uvwt({
                disabled: WeappTailwindcssDisabled
            }),
            AutoImport({
                imports: [
                    'vue',
                    'uni-app',
                    {
                        luxon: [
                            'DateTime',
                            'Duration',
                            'Interval',
                            'Settings'
                        ]
                    },
                    {
                        three: [
                            ['*', 'THREE']
                        ],
                        'three/examples/jsm/controls/OrbitControls': [
                            ['OrbitControls']
                        ]
                    }
                ],
                dts: false
            }),
            banner('/* codeflying */')
        ].filter(Boolean),
        css: {
            postcss: {
                plugins: [tailwindcss(), autoprefixer()]
            },
            preprocessorOptions: {
                scss: {
                    silenceDeprecations: ['legacy-js-api'],
                },
            },
        },
        server: {
            host: "0.0.0.0",
            proxy: {
                [env.VITE_APP_BASE_API]: {
                    target: env.VITE_APP_SERVICE_API,
                    changeOrigin: true,
                    rewrite: (path) => path.replace(new RegExp('^' + env.VITE_APP_BASE_API), ''),
                },
            }
        },
        resolve: {
            alias: {
                '~': path.resolve(__dirname, './'),
                '@': path.resolve(__dirname, './src')
            }
        },
        build: {
            // 🔥 小程序打包专属配置 - 只在这里改，不碰其他文件
            ...(isBuild && isMp ? {
                // 1. 启用terser压缩（比默认的esbuild压缩率高30%）
                minify: 'terser',
                terserOptions: {
                    compress: {
                        drop_console: true, // 删除所有console
                        drop_debugger: true, // 删除debugger
                        pure_funcs: ['console.log', 'console.info', 'console.debug', 'console.warn'],
                        passes: 2 // 多次优化，进一步压缩
                    },
                    output: {
                        comments: false // 删除注释
                    }
                },
                // 2. 不生成sourcemap（体积直接减半）
                sourcemap: false,
                // 3. 更小的chunk拆分
                rollupOptions: {
                    output: {
                        // 简化文件名，去掉timestamp
                        entryFileNames: `assets/[name].[hash].js`,
                        chunkFileNames: `assets/[name].[hash].js`,
                        assetFileNames: `assets/[name].[hash].[ext]`,
                        // 4. 关键：拆分大依赖
                        manualChunks(id) {
                            // 把three.js单独打包
                            if (id.includes('node_modules/three')) {
                                return 'vendor-three';
                            }
                            // 把luxon单独打包
                            if (id.includes('node_modules/luxon')) {
                                return 'vendor-luxon';
                            }
                            // 其他node_modules一起打包
                            if (id.includes('node_modules')) {
                                return 'vendor';
                            }
                        }
                    }
                },
                // 5. 降低chunk大小警告阈值
                chunkSizeWarningLimit: 500,
                // 6. 不压缩css（小程序会自己处理）
                cssMinify: false
            } : {
                // 非小程序环境（H5/App）保持原配置
                rollupOptions: {
                    output: {
                        entryFileNames: `assets/[name].[hash].t${timestamp}.js`,
                        chunkFileNames: `assets/[name].[hash].t${timestamp}.js`,
                        assetFileNames: `assets/[name].[hash].t${timestamp}.[ext]`
                    }
                }
            })
        }
    }
})