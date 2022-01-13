import { Component, Inject, OnInit } from '@angular/core';
import { Order } from './../../../model/customer';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { WebApiService } from 'src/app/services/web-api.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public order: Order, private api: WebApiService, private ref: MatDialogRef<OrderDetailsComponent>) { }

  ngOnInit() {
    console.log(this.order);
  }

}
