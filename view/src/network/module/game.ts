import type { AxiosPromise, AxiosResponse } from 'axios';
import type { Result } from "../type";
import { request } from '../request';
export function getGame(): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: '/game/allGame',
    method: "get"
  })
}
export function GameByID(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/game/GameByID/${id}`,
    method: "get"
  })
}
export function insertGame(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/game/insertGame`,
    method: "post",
    data
  })
}
export function updateGame(data: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/game/updateGame`,
    method: "put",
    data
  })
}
export function DeleteGame(id: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/game/updateGame/${id}`,
    method: "delete",
  })
}
export function GameByName(name: any): Promise<AxiosResponse<Result["data"], any>> {
  return request({
    url: `/game/GameByName/${name}`,
    method: "get"
  })
}
export default {
  getGame,
  GameByID,
  insertGame,
  updateGame,
  DeleteGame,
  GameByName
}