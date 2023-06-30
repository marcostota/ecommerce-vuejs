import { createRouter, createWebHistory } from 'vue-router'
import AddCategory from '../views/Category/AddCategory'
import Category from '../views/Category/Category'
import Home from '../views/Home'
import Admin from '../views/Admin'
import Product from '../views/Product/Product'
import AddProduct from '../views/Product/AddProduct.vue'
import EditCategory from '../views/Category/EditCategory.vue'
import EditProduct from '../views/Product/EditProduct.vue'
import ShowDetails from '../views/Product/ShowDetails.vue'
import ListProducts from '../views/Category/ListProducts.vue'
import Signup from '../views/Signup.vue'
import Signin from '../views/Signin.vue'
import Wishlist from '../views/Product/Wishlist.vue'
import Cart from '../views/Product/Cart.vue'
import Failed from '../views/payment/Failed.vue'
import Success from '../views/payment/Success.vue'
import Checkout from '../views/Checkout/Checkout.vue'


const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  // category detail page
  {
    path: '/category/show/:id',
    name: 'ListProducts',
    component: ListProducts
  },
  {
    path: '/admin/category/add',
    name: 'AddCategory',
    component: AddCategory
  },
  {
    path: '/admin/category',
    name: 'Category',
    component: Category
  },
  // category edit
  {
    path: '/admin/category/:id',
    name: 'EditCategory',
    component: EditCategory
  },
  // admin home page
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/admin/product',
    name: 'AdminProduct',
    component: Product
  },
  // add product
  {
    path: '/admin/product/new',
    name: 'AddProduct',
    component: AddProduct
  },
  // edit product
  {
    path: '/admin/product/:id',
    name: 'EditProduct',
    component: EditProduct
  },

  // show details of product
  {
    path: '/product/show/:id',
    name: 'ShowDetails',
    component: ShowDetails
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/signin',
    name: 'Signin',
    component: Signin
  },
  {
    path: '/wishlist',
    name: 'Wishlist',
    component: Wishlist
  },
  {
    path: '/Cart',
    name: 'Cart',
    component: Cart
  },
  {
    path: '/payment/success',
    name: 'Success',
    component: Success
  },
  {
    path: '/payment/failed',
    name: 'Failed',
    component: Failed
  },
  {
    path: '/checkout',
    name: 'Checkout',
    component: Checkout
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
