/**
 * @param {Object} context
 * @param  {...any} args
 * @return {any}
 */
Function.prototype.callPolyfill = function(context, ...args) {

    context = Object(context);

    const key = Symbol();

    context[key] = this;

    const result = context[key](...args);

    delete context[key];

    return result;
};