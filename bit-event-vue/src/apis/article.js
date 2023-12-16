import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js'

// 文章分类列表查询
export const articleCategoryListService = () => {
    const TokenStore = useTokenStore();
    // return request.get('/category', {headers:{
    //     'Authorization': TokenStore.token
    // }})
    return request.get('/category')
}

// 文章分类添加
export const articleCategoryaddService = (categoryData) => {
    return request.post('/category', categoryData)
}

// 文章分类编辑
export const articleCategoryUpdateService = (categoryData) => {
    return request.put('/category', categoryData)
}

// 文章分类删除
export const articleCategoryDeleteService = (categoryId) => {  
    return request.delete('/category?id=' + categoryId)
}

// 文章信息列表查询
export const articleListService = (params) => {
    return request.get('/article', {params: params})
}

// 文章信息添加
export const articleAddService = (articleData) => {
    return request.post('/article', articleData)
}

// 文章信息编辑
export const articleArticleEditService = (articleData) => {
    return request.put('/article', articleData)
}

// 文章信息删除
export const articleDeleteService = (articleId) => {
    return request.delete('/article?articleId=' + articleId)
}

// 文章信息查询
export const articleSelectService = (articleId) => {
    return request.get('/article/detail?articleId=' + articleId)
}