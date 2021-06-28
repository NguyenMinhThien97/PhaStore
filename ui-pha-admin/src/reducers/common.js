import * as Types from '../constants/ActionTypes';

var initialState = {};

const common = (state = initialState, action) => {
    switch (action.type) {
        case Types.GET_COMMON:
            return action.common;
        default:
            return state;
    }
}

export default common;