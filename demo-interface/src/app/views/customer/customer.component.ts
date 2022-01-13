import { ValuePipe } from './../../pipes/value.pipe';
import { WebApiService } from './../../services/web-api.service';
import { Product, ProductGroup, Option, OrderItem, Order } from './../../model/customer';
import { Component, OnInit } from '@angular/core';
import { timestamp } from 'rxjs';

@Component({
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {
  public customerId = "D1864532-CD55-4E81-A3CE-8D060B0FB15C";
  public order: Order = {
    total: 0,
    id: -1,
    items : [],
    customer: {
      id: 1
    },
    status: 0,
    orderNumber : 0,
    timestamp: new Date().toISOString()
  };
  public selectedGroup: ProductGroup|null = null;
  public selectedProduct: Product|null = null;
  public selectedItem: OrderItem|null = null;
  public groups: ProductGroup[] = [];
  public products: Product[] = [];

  public total: number = 0;
  constructor(private api: WebApiService) { }

  ngOnInit(): void {
      this.api.getProductGroups().then(
        e=> this.groups = e
      );
      this.api.getActiveOrder(this.customerId).then(
        e=> {
          if(e.length > 0){
            this.order = e[0];
            this.order.items.forEach(pos=>pos.value = (pos.price + pos.options.reduce((p,o)=>p + (o.priceModifier * o.quantity),0)) * pos.quantity);
            this.total = this.order.items.reduce((p,i)=>p + i.value,0);
          }
        }
      );
  }
  selectGroup(group: ProductGroup){
    this.selectedGroup = group;
    this.products = [];
    this.api.getProducts(group.id).then(e=>this.products = e);
  }
  async selectProduct(product: Product){
    const pos : OrderItem =  {
      id : -1,
      value: 0,
      options:[],

      price : product.price,
      product : product,
      quantity : 1
    }
    if(product.options.length > 0){
      this.selectedProduct = product;
      this.selectedItem = pos;
    }
    await this.addItemToOrder(pos,this.selectedProduct != null);
  }
  async addItemToOrder(pos: OrderItem, selected: boolean) {
    console.log(this.order.id);
    if(this.order.id == -1){
      console.log("insert");
      this.order.timestamp =  new Date().toISOString();

      this.order.id = (await this.api.insertOrder(this.order)).id;
    }
    console.log(this.order.id);
    let added = false;
    await Promise.all(this.order.items.map( async e=>{
      if(!added && e.product.id == pos.product.id && !selected){
        e.quantity += pos.quantity;
        e.value = (e.price + e.options.reduce((p,o)=>p + (o.priceModifier * o.quantity),0)) * e.quantity;
        added = true;
        await this.api.updateOrderItem(e);
      }
    }));
    if(!added){
      pos.value = (pos.price + pos.options.reduce((p,o)=>p + (o.priceModifier * o.quantity),0)) * pos.quantity;
      pos.id = (await this.api.insertOrderItem(pos,this.order.id)).id;
      this.order.items.push(pos);
    }
    this.total = this.order.items.reduce((p,i)=>p + i.value,0);
  }
  async selectOption(option: Option){
    let added = false;
    await Promise.all(this.selectedItem!.options.map ( async e=>{
      if(!added && e.option.id == option.id ){
        e.quantity += 1;
        added = true;
        await this.api.updateOrderItemOption(e);
      }
    }));
    if(!added){
      const opt =await  this.api.insertOrderItemOption({
        id : -1,
        option : option,
        priceModifier : option.priceModifier,
        quantity : 1
      },this.selectedItem!.id);
      this.selectedItem!.options.push(opt);
    }
    this.selectedItem!.value = ( this.selectedItem!.price +  this.selectedItem!.options.reduce((p,o)=>p + (o.priceModifier * o.quantity),0)) *  this.selectedItem!.quantity;
    this.total = this.order.items.reduce((p,i)=>p + i.value,0);
  }
  itemValue(item: OrderItem){


  }

  async placeOrder(){
    this.order.status = 1;
    this.order.timestamp =  new Date().toISOString();
    await this.api.updateOrder(this.order);
    alert("Order placed successfully!!");
    this.selectedGroup = null;
    this.selectedItem = null;
    this.selectedProduct = null;
    this.order = {
      id: -1,
      items : [],
      customer: {
        id: 1
      },
      total: 0,
      status: 0,
      orderNumber : 0,
      timestamp: new Date().toISOString()
    };
  }


}
