import axios from 'axios';
import * as Config from './../constants/Config';

const username = 'admin'
const password = 'phaStore'

const token = Buffer.from(`${username}:${password}`, 'utf8').toString('base64')

export default function callApi(endpoint, method = 'GET', body) {
    return axios({
        headers: {
            'Authorization': `Basic ${token}`
        },
        method: method,
        url: `${Config.API_URL}/${endpoint}`,
        data: body
    }).catch(err => {
        console.log(err);
    });
};