

export interface Order{
  id: number;
  orderNumber: number;
  timestamp: string;
  customer: Customer;
  items: OrderItem[];
  status: number;
  total: number;
}
export interface Customer{
  id: number,
  firstName?: string,
  lastName?: string,
}
export interface OrderItem{
  id: number;
  product: Product;
  options: OrderItemOption[];
  price: number;
  quantity: number;
  value: number;
}
export interface OrderItemOption{
  id: number;
  option: Option;
  priceModifier: number;
  quantity: number;
}
export interface ProductGroup{
  id: number;
  name: string;
  image: string;
  products: Product[];
}
export interface Product{
  id: number;
  name: string;
  description: string;
  image: string;
  price: number;
  options: Option[]
}
export interface Option{

  id: number;
  name: string;
  priceModifier: number;
}
