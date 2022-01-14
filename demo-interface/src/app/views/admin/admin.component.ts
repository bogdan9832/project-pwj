import { OrderDetailsComponent } from './order-details/order-details.component';
import { EditOptionDialogComponent } from './edit-option-dialog/edit-option-dialog.component';
import { EditProductDialogComponent } from './edit-product-dialog/edit-product-dialog.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  Option,
  Order,
  OrderItem,
  Product,
  ProductGroup,
} from 'src/app/model/customer';
import { WebApiService } from 'src/app/services/web-api.service';
import { EditGroupDialogComponent } from './edit-group-dialog/edit-group-dialog.component';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent implements OnInit {



  public dateRange: FormGroup = new FormGroup(
    {
      start: new FormControl(new Date(new Date().getTime() - 24 * 60 * 60 * 1000 )),
      end: new FormControl(new Date()),
    }
  )

  public isSingleClick = true;
  public selectedGroup: ProductGroup | null = null;
  public selectedProduct: Product | null = null;
  public selectedItem: OrderItem | null = null;
  public groups: ProductGroup[] = [];
  public products: Product[] = [];
  public orders : Order[] = [];
  constructor(private api: WebApiService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.api.getProductGroups().then((e) => (this.groups = e));
    this.getOrders(this.dateRange.value.start,this.dateRange.value.end);
    this.dateRange.valueChanges.subscribe(
      v=>{

          if(v.start != null && v.end != null){
            console.log(v.start,v.end);
            this.getOrders(v.start,v.end);

          }
      }
    )
  }

  getOrders(start: Date,end : Date){
    this.api.getOrders(start,end).then(
      e=> {
       e.forEach(e=> {
         e.items.forEach(i=> i.value = (i.price +  i.options.reduce((p,o)=>p + (o.priceModifier * o.quantity),0)) * i.quantity);
        e.total = e.items.reduce((p,i)=>p + i.value,0);
       });
        this.orders = e

      });
  }
  selectGroup(group: ProductGroup) {
    this.isSingleClick = true;
    setTimeout(() => {
      console.log('single', this.isSingleClick);
      if (this.isSingleClick) {
        this.api.getProducts(group.id).then((e) => {
          this.products = e;
          this.selectedGroup = group;
        });
      }
    }, 250);
  }
  editGroup(group: ProductGroup) {
    this.isSingleClick = false;

    const ref = this.dialog.open(EditGroupDialogComponent, {
      data: { ...group },
    });
    ref.afterClosed().subscribe((g) => {
      if (g) {
        group.id = g.id;
        group.image = g.image;
        group.name = g.name;
      }
    });
    // open group edit;
  }

  editProduct(product: Product) {
    this.isSingleClick = false;
    const ref = this.dialog.open(EditProductDialogComponent, {
      data: { product: { ...product }, group: this.selectedGroup!.id },
    });
    ref.afterClosed().subscribe((p) => {
      if (p) {
        product.id = p.id;
        product.image = p.image;
        product.name = p.name;
        product.price = p.price;
        product.description = product.description;
      }
    });
  }
  editOption(option: Option) {
    this.isSingleClick = false;
    const ref = this.dialog.open(EditOptionDialogComponent, {
      data: { option: { ...option }, product: this.selectedProduct!.id },
    });
    ref.afterClosed().subscribe((o) => {
      if (o) {
        option.id = o.id;
        option.name = o.name;
        option.priceModifier = o.priceModifier;
      }
    });
  }
  selectProduct(product: Product) {
    this.isSingleClick = true;
    setTimeout(() => {
      if (this.isSingleClick) this.selectedProduct = product;
    }, 250);
  }
  async deleteGroup(group: ProductGroup) {
    await this.api.deleteGroup(group);
    this.groups.splice(
      this.groups.findIndex((e) => e.id == group.id),
      1
    );
  }
  async deleteProduct(product: Product) {
    await this.api.deleteProduct(product);
    this.products.splice(
      this.products.findIndex((e) => e.id == product.id),
      1
    );
  }
  async deleteOption(option: Option) {
    await this.api.deleteOption(option);
    this.selectedProduct!.options.splice(
      this.selectedProduct!.options.findIndex((e) => e.id == option.id),
      1
    );
  }

  createGroup() {
    this.isSingleClick = false;

    const ref = this.dialog.open(EditGroupDialogComponent, {
      data: {
        id: -1,
        image: '',
        name: '',
        products: [],
      },
    });
    ref.afterClosed().subscribe((g) => {
      if (g) {
        this.groups.push(g);
      }
    });
    // open group edit;
  }

  createProduct() {
    this.isSingleClick = false;
    const ref = this.dialog.open(EditProductDialogComponent, {
      data: {
        product: {
          id: -1,
          image: '',
          name: '',
          price:0,
          description: '',
          options: [],
        },
        group: this.selectedGroup!.id,
      },
    });
    ref.afterClosed().subscribe((g) => {
      if (g) {
        this.products.push(g);
      }
    });
  }
  createOption() {

    this.isSingleClick = false;
    const ref = this.dialog.open(EditOptionDialogComponent, {
      data: { option: {
        id : -1,
        name : "",
        priceModifier : 0
      }, product: this.selectedProduct!.id },
    });
    ref.afterClosed().subscribe((o) => {
      if (o) {
        this.selectedProduct?.options.push(o);
      }
    });
  }

  showOrder(order: Order){
    this.dialog.open(
      OrderDetailsComponent,
      {
        data: order,
        minWidth: '60vw',
        minHeight: '60vh'
      },

    )
  }
}
