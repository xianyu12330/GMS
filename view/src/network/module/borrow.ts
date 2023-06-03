import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';
export function getBorrow(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/borrow/allBorrow'
  })
}
export function BorrowByID(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrow/BorrowByID/${id}`,
    method: 'get'
  })
}
export function insertBorrow(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrow/insertBorrow`,
    method: 'post',
    data
  })
}
export function updateBorrow(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrow/updateBorrow`,
    method: 'put',
    data
  })
}
export function DeleteBorrow(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrow/deleteBorrow/${id}`,
    method: 'delete'
  })
}
export function BorrowByUser(name: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/borrow/borrowByUser/${name}`,
    method: 'get'
  })
}

export default {
  getBorrow,
  BorrowByID,
  insertBorrow,
  updateBorrow,
  DeleteBorrow,
  BorrowByUser
}