import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';

export function getBorrow():Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: '/borrow/allBorrow'
  })
}
export function BorrowByID(id : any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/borrow/BorrowByID/${id}`,
    method: 'get'
  })
}
export function insertBorrow(data:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/borrow/insertBorrow`,
    method: 'post',
    data
  })
}
export function updateBorrow(data:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/borrow/updateBorrow`,
    method: 'put',
    data
  })
}
export function DeleteBorrow(id:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/borrow/deleteBorrow/{id}`,
    method: 'delete'
  })
}
export function getEquipment():Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/equip/allEquip`,
  })
}
export function EquipByID(id:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/equip/allEquip/${id}`,
    method:'get'
  })
}
export function insertEquip(data:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/equip/insertEquip`,
    method:'post'
  })
}
export function updateEquip(data:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/equip/updateEquip`,
    method:'put'
  })
}
export function DeleteEquip(id:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/equip/deleteEquip/${id}`,
    method:'delete'
  })
}


export default {
  getBorrow,
  BorrowByID,
  insertBorrow,
  updateBorrow,
  DeleteBorrow,
  getEquipment,
  EquipByID,
insertEquip,
updateEquip,
DeleteEquip
}