import {
  Order,
  Product,
  ProductGroup,
  OrderItem,
  OrderItemOption,
  Option,
} from './../model/customer';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebApiService {
  private baseUrl = 'http://localhost:8081';
  constructor(private http: HttpClient) {}
  public getProductGroups(): Promise<ProductGroup[]> {
    return firstValueFrom(
      this.http.get<ProductGroup[]>(`${this.baseUrl}/groups`)
    );
  }
  public getProducts(group: number): Promise<Product[]> {
    return firstValueFrom(
      this.http.get<Product[]>(`${this.baseUrl}/products/${group}`)
    );
  }
  public getActiveOrder(customer: string): Promise<Order[]> {
    return firstValueFrom(
      this.http.get<Order[]>(`${this.baseUrl}/orders/0/${customer}`)
    );
  }
  public insertOrder(order: Order): Promise<Order> {
    return firstValueFrom(
      this.http.post<Order>(`${this.baseUrl}/orders`, order)
    );
  }
  public insertOrderItem(
    orderItem: OrderItem,
    orderId: number
  ): Promise<OrderItem> {
    return firstValueFrom(
      this.http.post<OrderItem>(`${this.baseUrl}/items/${orderId}`, orderItem)
    );
  }
  public updateOrderItem(orderItem: OrderItem): Promise<OrderItem> {
    return firstValueFrom(
      this.http.patch<OrderItem>(`${this.baseUrl}/items`, orderItem)
    );
  }

  public insertOrderItemOption(
    option: OrderItemOption,
    orderItemId: number
  ): Promise<OrderItemOption> {
    return firstValueFrom(
      this.http.post<OrderItemOption>(
        `${this.baseUrl}/item-options/${orderItemId}`,
        option
      )
    );
  }

  public updateOrderItemOption(
    option: OrderItemOption
  ): Promise<OrderItemOption> {
    return firstValueFrom(
      this.http.patch<OrderItemOption>(`${this.baseUrl}/item-options`, option)
    );
  }
  public updateOrder(order: Order): Promise<Order> {
    return firstValueFrom(
      this.http.patch<Order>(`${this.baseUrl}/orders`, order)
    );
  }


  public insertGroup(group: ProductGroup): Promise<ProductGroup> {
    return firstValueFrom(
      this.http.post<ProductGroup>(`${this.baseUrl}/groups`, group)
    );
  }
  public updateGroup(group: ProductGroup): Promise<ProductGroup> {
    return firstValueFrom(
      this.http.patch<ProductGroup>(`${this.baseUrl}/groups`, group)
    );
  }

  public deleteGroup(group: ProductGroup): Promise<any> {
    return firstValueFrom(
      this.http.delete<any>(`${this.baseUrl}/groups/${group.id}`)
    );
  }


  public insertProduct(product: Product, group: number): Promise<Product> {
    return firstValueFrom(
      this.http.post<Product>(`${this.baseUrl}/products/${group}`, product)
    );
  }
  public updateProduct(product: Product): Promise<Product> {
    return firstValueFrom(
      this.http.patch<Product>(`${this.baseUrl}/products`, product)
    );
  }

  public deleteProduct(product: Product): Promise<void> {
    return firstValueFrom(
      this.http.delete<void>(`${this.baseUrl}/products/${product.id}`)
    );
  }


  public insertOption(option: Option, product : number): Promise<Option> {
    return firstValueFrom(
      this.http.post<Option>(`${this.baseUrl}/options/${product}`, option)
    );
  }
  public updateOption(option: Option): Promise<Option> {
    return firstValueFrom(
      this.http.patch<Option>(`${this.baseUrl}/options`, option)
    );
  }

  public deleteOption(option: Option): Promise<void> {
    return firstValueFrom(
      this.http.delete<void>(`${this.baseUrl}/options/${option.id}`)
    );
  }

  public getOrders(startDate: Date, endDate: Date){
    return firstValueFrom(
      this.http.get<Order[]>(`${this.baseUrl}/orders/dates/${startDate.toISOString().split('T')[0]}/${endDate.toISOString().split('T')[0]}`)
    )
  }
}
