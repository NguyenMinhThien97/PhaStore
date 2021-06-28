import * as Types from '../constants/ActionTypes';
import callApi from '../utils/apiCaller';

export const getCommonRequest = (commonCode) => {
    return dispatch => {
        return callApi(`common/findCommon/${commonCode}`, 'GET', null).then(res => {
            dispatch(getCommon(res.data));
        });
    }
}
export const getCommon = (common) => {
    return {
        type: Types.GET_COMMON,
        common
    }
}