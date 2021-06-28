import { combineReducers } from 'redux';
import common from './Common/common';
import label from './Common/label';
import message from './Common/message';


const appReducers = combineReducers({
    common,
    label,
    message,
});

export default appReducers;
