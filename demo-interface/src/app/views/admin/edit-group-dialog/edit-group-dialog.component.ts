import { ProductGroup } from './../../../model/customer';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { WebApiService } from 'src/app/services/web-api.service';

@Component({
  selector: 'app-edit-group-dialog',
  templateUrl: './edit-group-dialog.component.html',
  styleUrls: ['./edit-group-dialog.component.scss']
})
export class EditGroupDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public group: ProductGroup, private api: WebApiService, private ref: MatDialogRef<EditGroupDialogComponent>) { }

  ngOnInit() {
  }

  async save(){
      if(this.group.id == -1){
        this.group.id = await (await this.api.insertGroup(this.group)).id;
      }
      else
        await this.api.updateGroup(this.group);
      this.ref.close(this.group);
  }

}
