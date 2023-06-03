import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';
export function getBorrowAdd(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/borrowAdd/allBorrow'
  })
}
export function BorrowAddByID(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrowAdd/BorrowById/${id}`,
    method: 'get'
  })
}
export function insertBorrowAdd(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrowAdd/insertBorrow`,
    method: 'post',
    data
  })
}
export function updateBorrowAdd(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrowAdd/updateBorrow`,
    method: 'put',
    data
  })
}
export function DeleteBorrowAdd(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrowAdd/deleteBorrow/${id}`,
    method: 'delete'
  })
}
export function BorrowAddByName(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrowAdd/borrowAddByUser/${id}`,
    method: 'get'
  })
}

export default {
  getBorrowAdd,
  BorrowAddByID,
  insertBorrowAdd,
  updateBorrowAdd,
  DeleteBorrowAdd,
  BorrowAddByName
}