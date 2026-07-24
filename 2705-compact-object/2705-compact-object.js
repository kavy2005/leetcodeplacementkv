/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {

    if (obj === null) return obj;

    if (Array.isArray(obj)) {

        const res = [];

        for (const item of obj) {

            const val = compactObject(item);

            if (Boolean(val))
                res.push(val);
        }

        return res;
    }

    if (typeof obj === "object") {

        const res = {};

        for (const key in obj) {

            const val = compactObject(obj[key]);

            if (Boolean(val))
                res[key] = val;
        }

        return res;
    }

    return obj;
};