const storage = {
    getItem(key) {
        try {
            return uni.getStorageSync(key)
        } catch (e) {
            console.warn("No localStorage available");
            return null;
        }
    },
    setItem(key, value) {
        try {
            uni.setStorageSync(key, value);
        } catch (e) {
            console.warn("Cannot write to localStorage");
        }
    },
    removeItem(key) {
        try {
            uni.removeStorageSync(key)
        } catch (e) {
            console.warn("Cannot remove from localStorage");
        }
    }
};

export default storage;