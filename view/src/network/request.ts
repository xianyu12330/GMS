import axios from 'axios';
import type { AxiosRequestConfig, AxiosResponse } from "axios";
import { ElMessage } from 'element-plus'

const instance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 5000,
})

//------------------请求拦截-------------------//
//------------在发送请求之前做些什么------------//
instance.interceptors.request.use((config: AxiosRequestConfig) => {
  if (config.headers === undefined) return config;
  config.headers['Content-Type'] = 'application/json'
  // 若存在token则带token
  const token = window.localStorage.getItem('token');
  if (token) {
    config.headers.token = token;
  }
  // 放行
  return config;
}, err => {
  console.log("请求拦截错误=>", err);
})

//------------------响应拦截-------------------//
//-------------对响应数据做点什么-------------//
instance.interceptors.response.use((res: AxiosResponse<any, any>) => {
  //例：后端数据处理错误，并返回错误原因；前端获取错误原因并展示
  // console.log("响应拦截=>", res.data);

  return res.data;


}, (err: any) => {
  console.log(err.response);
  //也可以在这里对不同的错误码做不同的展示处理
  if (err.response.status == 500)
    ElMessage.error('网络错误，请检查网络！')
  throw err
})


//导出request方法
export function request(config: AxiosRequestConfig) {
  return instance(config);
}