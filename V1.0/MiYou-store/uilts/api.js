import axios from 'axios'
import { Message } from 'element-ui'

// 响应拦截器
axios.interceptors.response.use(
  success => {
    // 业务逻辑错误
    if (success.status && success.status === 200) {
      if (success.data.code === 500 || success.data.code === 401 || success.data.code === 403 || success.data.code === 400) {
        Message.error({ message: success.data.message })
        return
      }
      if (success.data.message) {
        Message.success({ message: success.data.message })
      }
    }
    return success.data
  }, error => {
    if (error.response.code === 504 || error.response.code === 404) {
      Message.error({ message: '服务器被吃了' })
    } else if (error.response.code === 403) {
      Message.error({ message: '权限不足' })
    } else if (error.response.code === 401) {
      Message.error({ message: '未登录，请求前往登录' })
    } else {
      if (error.response.data.message) {
        Message.error({ message: error.response.data.message })
      } else {
        Message.error({ message: '未知错误' })
      }
    }
  })

export const postRequst = (url, params) => {
  return axios({
    method: 'post',
    url: `${url}`,
    data: params
  })
}

export const getRequst = (url, params) => {
  return axios({
    method: 'get',
    url: `${url}`,
    params: params
  })
}
