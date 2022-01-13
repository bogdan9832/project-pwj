import { Product } from './../../../model/customer';
import { WebApiService } from 'src/app/services/web-api.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-product-dialog',
  templateUrl: './edit-product-dialog.component.html',
  styleUrls: ['./edit-product-dialog.component.scss']
})
export class EditProductDialogComponent implements OnInit {
public product :Product;
public group: number;
  constructor(@Inject(MAT_DIALOG_DATA) data: any, private api: WebApiService, private ref: MatDialogRef<EditProductDialogComponent>) {
      this.product = data.product;
      this.group = data.group;
  }

  ngOnInit() {
  }

  async save(){
      if(this.product.id == -1){
        this.product.id = await (await this.api.insertProduct(this.product,this.group)).id;
      }
      else
        await this.api.updateProduct(this.product);
      this.ref.close(this.product);
  }
}
