import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';

<<<<<<< HEAD
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
=======

export function getEquipment(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/allEquip`,
    method: 'get'
  })
}
export function EquipByID(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/EquipByID/${id}`,
    method: 'get'
  })
}
export function insertEquip(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/insertEquip`,
>>>>>>> 0c4f1a5 (修改了数据库和后端代码)
    method: 'post',
    data
  })
}
<<<<<<< HEAD
export function updateBorrow(data:any):Promise<AxiosResponse<Result["data"],any>>{
  return request({
    url: `/borrow/updateBorrow`,
=======
export function updateEquip(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/updateEquip`,
>>>>>>> 0c4f1a5 (修改了数据库和后端代码)
    method: 'put',
    data
  })
}
<<<<<<< HEAD
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
=======
export function DeleteEquip(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/deleteEquip/${id}`,
    method: 'delete'
  })
}

export function equipByName(EName: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/equipByName/${EName}`,
    method: 'get'
  })
}
export function GetFreeEquip(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/freeEquip`,
    method: 'get'
  })
}
export function GetCount(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/GetCount/${id}`,
    method: "get",
  })
}

export function changeEquipState(ename: any, num: any, state: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/equip/changeEquipState/${ename}/${num}/${state}`,
    method: 'put',
  })
}
export default {

  getEquipment,
  EquipByID,
  insertEquip,
  updateEquip,
  DeleteEquip,
  equipByName,
  GetFreeEquip,
  GetCount,
  changeEquipState
>>>>>>> 0c4f1a5 (修改了数据库和后端代码)
}