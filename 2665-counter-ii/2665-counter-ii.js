/**
 * @param {integer} init
 * @return { increment: Function, decrement: Function, reset: Function }
 */
var createCounter = function(init) {

    let value = init;

    return {

        increment() {
            return ++value;
        },

        decrement() {
            return --value;
        },

        reset() {
            value = init;
            return value;
        }

    };

};