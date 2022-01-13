import { Order, OrderItem } from './../model/customer';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'value'
})
export class ValuePipe implements PipeTransform {

  transform(value: OrderItem | Order, args?: any): number {
    if((value as Order).items){
        return (value as Order).items.reduce((p,i)=>p + this.transform(i),0);
    }
    else {
      console.log(value);
      const item :OrderItem = value as OrderItem;
      return (item.price + item.options.reduce((p,o)=>p + (o.priceModifier * o.quantity),0)) * item.quantity ;
    }

  }

}
