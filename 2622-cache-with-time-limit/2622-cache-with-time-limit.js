/**
 * @return {TimeLimitedCache}
 */
var TimeLimitedCache = function() {
    this.cache = new Map();
};

/**
 * @param {number} key
 * @param {number} value
 * @param {number} duration
 * @return {boolean}
 */
TimeLimitedCache.prototype.set = function(key, value, duration) {

    const now = Date.now();

    const exists =
        this.cache.has(key) &&
        this.cache.get(key).expire > now;

    this.cache.set(key,{
        value:value,
        expire:now+duration
    });

    return exists;
};

/**
 * @param {number} key
 * @return {number}
 */
TimeLimitedCache.prototype.get = function(key) {

    const item=this.cache.get(key);

    if(!item || item.expire<=Date.now())
        return -1;

    return item.value;
};

/**
 * @return {number}
 */
TimeLimitedCache.prototype.count = function() {

    let ans=0;

    const now=Date.now();

    for(const value of this.cache.values()){

        if(value.expire>now)
            ans++;

    }

    return ans;
};