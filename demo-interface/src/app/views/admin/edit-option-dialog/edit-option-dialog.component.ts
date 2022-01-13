
import { WebApiService } from 'src/app/services/web-api.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Option } from 'src/app/model/customer';

@Component({
  selector: 'app-edit-option-dialog',
  templateUrl: './edit-option-dialog.component.html',
  styleUrls: ['./edit-option-dialog.component.scss']
})
export class EditOptionDialogComponent implements OnInit {
  public option: Option;
  public product: number;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private api: WebApiService, private ref: MatDialogRef<EditOptionDialogComponent>) {
    this.product = data.product;
    this.option = data.option;
  }

  ngOnInit() {
  }

  async save(){
      if(this.option.id == -1){
        this.option.id = await (await this.api.insertOption(this.option,this.product)).id;
      }
      else
        await this.api.updateOption(this.option);
      this.ref.close(this.option);
  }
}
