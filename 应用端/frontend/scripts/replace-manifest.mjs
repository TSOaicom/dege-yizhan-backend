import fs from "fs";
import path from "path";
import { loadEnv } from 'vite';

const mode = process.env.MODE || 'development';
const env = loadEnv(mode, process.cwd());

const templatePath = path.resolve(process.cwd(), "src/manifest.template.json");
const manifestPath = path.resolve("src/manifest.json");
fs.copyFileSync(templatePath, manifestPath);


let manifest = fs.readFileSync(manifestPath, "utf-8");

const mapType = env.VITE_MAP_TYPE;
const qqKey = env.VITE_QQ_MAP_KEY || "";
const googleKey = env.VITE_GOOGLE_MAP_KEY || "";
console.log(env, mapType, qqKey)

// 替换 placeholder
manifest = manifest
    .replace(/"__QQ_MAP_KEY__"/g, `"${qqKey}"`)
    .replace(/"__GOOGLE_MAP_KEY__"/g, `"${googleKey}"`);

if (mapType === "qq") {
    manifest = manifest.replace(
        /"google":\s*\{[\s\S]*?\}/,
        `"google": {}`
    );
} else if (mapType === "google") {
    manifest = manifest.replace(
        /"qqmap":\s*\{[\s\S]*?\}/,
        `"qqmap": {}`
    );
}

fs.writeFileSync(manifestPath, manifest, "utf-8");

console.log(`Manifest maps config updated => ${mapType}`);
