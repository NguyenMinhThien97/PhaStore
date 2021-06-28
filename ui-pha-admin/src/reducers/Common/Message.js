import * as Types from '../../constants/ActionTypes';

var initialState = {};

const message = (state = initialState, action) => {
    switch (action.type) {
        case Types.GET_MSG:
            return action.message;
        default:
            return state;
    }
}

export default message;
