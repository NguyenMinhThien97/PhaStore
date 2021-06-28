import * as Types from '../../constants/ActionTypes';

var initialState = {};

const label = (state = initialState, action) => {
    switch (action.type) {
        case Types.GET_LABEL:
            return action.label;
        default:
            return state;
    }
}

export default label;
