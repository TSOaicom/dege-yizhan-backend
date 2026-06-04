<template>
	<div class="w-full h-full relative">
		<!-- #ifdef APP -->
		<!-- app建议自己写逻辑，因为是renderjs的方式，调用方法很麻烦，自己写逻辑可以在template中直接调用renderjs中的方法 -->
		<StageApp v-bind="$props" @loaded="loaded" :playOptions="playOptions"></StageApp>
		<!-- #endif -->

		<!-- #ifndef APP -->
		<base-three-container @onload="onload"></base-three-container>
		<!-- #endif -->
	</div>
</template>

<script>
	// #ifdef APP
	import StageApp from './stageApp.vue'
	// #endif	

	// #ifndef APP
	// import Stage from '../base-three-container.vue'
	import * as THREE from './lib.js'
	import {
		useThreeJs,
		traverse,
		events,
		animation
	} from '@/components/threeJs/index.js'
	const {
		getInstance, // 获取canvas、camera、等实例
		centerAndScale, // 用于调整模型大小和位置
		setEnv, // 设置环境贴图
		screenshot: _screenshot, // 截图
		fixFbxMaterial
	} = useThreeJs()
	// #endif

	export default {
		name: 'preview3D',
		props: {
			// 模型链接
			modelurl: {
				type: [String, ArrayBuffer],
				default: ''
			},
			// 手动指定模型类型，比如h5预览本地模型可能会用到
			modelType: {
				type: String,
				default: '', // 'glb'
			},
			// 环境贴图链接
			env: {
				type: String,
				default: ''
			},
			// 环境贴图是否可见
			backgroundVisible: {
				type: Boolean,
				default: false
			},
			// 模型缩放比例
			scale: {
				type: Number,
				default: 0.6
			},
			// 模型自动旋转
			autoRotate: {
				type: Boolean,
				default: false
			},
			// 自动旋转速度
			autoRotateSpeed: {
				type: Number,
				default: 1.6
			},
			isFixFbxMaterial: {
				type: Boolean,
				default: false
			}
		},

		components: {
			// #ifndef APP
			Stage,
			// #endif
			// #ifdef APP
			StageApp
			// #endif
		},
		// #ifndef APP
		watch: {
			'modelurl': {
				handler() {
					this.loadModel()
				}
			},
			'autoRotate': {
				handler(val) {
					this.update()
				}
			},
			'autoRotateSpeed': {
				handler(val) {
					this.update()

				}
			},
		},
		// #endif
		data() {
			return {
				playOptions: {},
			}
		},
		methods: {
			update() {
				const {
					controls
				} = getInstance()
				controls.autoRotateSpeed = this.autoRotateSpeed
				controls.autoRotate = this.autoRotate
			},
			loaded() {
				this.$emit('loaded')
			},
			// 非app端，场景初始化完毕
			async onload() {
				// 设置环境贴图
				const {
					backgroundVisible,
					env
				} = this.$props
				setEnv(env, backgroundVisible)
				this.loadModel()
				this.update()
			},
			// 加载模型
			async loadModel() {
				const {
					modelurl,
					scale,
					isFixFbxMaterial,
					modelType
				} = this.$props
				const {
					group,
					loader,
					scene,
					controls
				} = getInstance()
				controls.reset() // 重置视角

				;
				[...group.children].forEach(c => { // 切换模型需要删除上一个模型
					c.removeFromParent()
					// group.remove(c)
				})


				const result = await loader.load(modelurl, null, modelType)
				group.add(result?.scene || result)
				isFixFbxMaterial && fixFbxMaterial(group)

				centerAndScale(scale)

				this.loaded()
			},
			async play(animationName, needReverse = false, attr = {}) {
				// #ifndef APP
				const {
					group,
				} = getInstance()
				const [model] = [...group.children]
				await animation.play(model, animationName, needReverse, attr)
				// #endif

				// #ifdef APP
				this.playOptions = {
					animationName,
					needReverse,
					attr
				}

				this.$nextTick(() => {
					this.playOptions = {}
				})
				// #endif

			},
		},
		mounted() {
			// 
		}
	}
</script>


<style lang="scss" scoped>
	.w-full {
		width: 100%;
	}

	.h-full {
		height: 100%;
	}

	.relative {
		position: relative;
	}
</style>