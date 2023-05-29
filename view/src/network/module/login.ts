import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';

function login(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/login/log',
    method: 'post',
    data
  })
}
function register(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/login/register',
    method: 'post',
    data
  })
}

export default {
  login,
  register,
}