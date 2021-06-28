import * as Types from '../constants/ActionTypes';
import callApi from '../utils/apiCaller';

//common
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

// label
export const getLabelRequest = (labels) => {
    return dispatch => {
        return callApi('common/getLabel', 'POST', labels).then(res => {
            dispatch(getLabel(res.data));
        });
    }
}
export const getLabel = (label) => {
    return {
        type: Types.GET_LABEL,
        label
    }
}

//message
export const getMSGRequest = (messageCode) => {
    return dispatch => {
        return callApi(`common/getMessage/${messageCode}`, 'GET', null).then(res => {
            dispatch(getMSG(res.data));
        });
    }
}
export const getMSG = (message) => {
    return {
        type: Types.GET_MSG,
        message
    }
}
