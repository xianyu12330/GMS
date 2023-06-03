import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';
export function getPlaces(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/places/allplaces',
    method: "get"
  })
}
export function PlaceByID(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/places/PlaceByID/${id}`,
    method: "get"
  })
}
export function insertPlace(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/places/insertPlace`,
    method: "post",
    data
  })
}
export function updatePlace(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/places/updatePlace`,
    method: "put",
    data
  })
}
export function DeletePlace(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/places/deletePlace/${id}`,
    method: "delete",
  })
}
export function GetFreePlace(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/places/freePlace/`,
    method: "get",
  })
}
export function changeState(name: any, state: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/places/changeState/${name}/${state}`,
    method: "put",
  })
}

export default {
  getPlaces,
  PlaceByID,
  insertPlace,
  updatePlace,
  DeletePlace
}

