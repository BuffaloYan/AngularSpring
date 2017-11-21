import { Component, DoCheck, Inject } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Product } from "../model/product.model";
import { Model } from "../model/repository.model";
import { MODES, SharedState, SHARED_STATE } from "./sharedState.model";
import { Observable } from 'rxjs/Observable';

@Component({
    selector: "paForm",
    moduleId: module.id,
    templateUrl: "form.component.html",
    styleUrls: ["form.component.css"]
})
export class FormComponent {
    product: Product = new Product();
    lastId: number;
  
    constructor(public model: Model,       
          @Inject(SHARED_STATE)
            public stateEvents: Observable<SharedState>) { 
      stateEvents.subscribe( (update) => {
        if( update.mode === MODES.EDIT ) {
          this.product = { ...this.model.getProduct(update.id) };
          this.editing = true;
        } else {
          this.resetForm();
          this.editing = false;
        }
      } );
    }
    
    editing: boolean = false;
    
    submitForm(form: NgForm) {
      if (form.valid) {
          this.model.saveProduct(this.product);          
          this.product = new Product();
          form.reset();       
        }
    }

    resetForm() {
        this.product = new Product();
    }
  
}
