import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';

export function getUserList(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/user/allUser'
  })
}

export function userById(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/user/UserById/${id}`,
    method: 'get'
  })
}
export function addUser(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/user/insertUser',
    method: 'post',
    data
  })
}

export function deleteUser(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/user/deleteUser/${id}`,
    method: 'delete'
  })
}
export function updateUser(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/user/updateUser',
    method: 'put',
    data
  })
}
export function getRole(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/user/getRole/${id}`,
    method: 'get'
  })
}
export function userByName(name: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/user/userByName/${name}`,
    method: 'get'
  })
}
export function updateRole(id: any, role: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/user/updateRole/${id}/${role}`,
    method: 'put'
  })
}
export default {
  getUserList,
  userById,
  addUser,
  deleteUser,
  updateUser,
  getRole,
  userByName,
  updateRole
}