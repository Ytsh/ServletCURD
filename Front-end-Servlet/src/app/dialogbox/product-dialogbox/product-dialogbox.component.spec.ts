import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductDialogboxComponent } from './product-dialogbox.component';

describe('ProductDialogboxComponent', () => {
  let component: ProductDialogboxComponent;
  let fixture: ComponentFixture<ProductDialogboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductDialogboxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductDialogboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
