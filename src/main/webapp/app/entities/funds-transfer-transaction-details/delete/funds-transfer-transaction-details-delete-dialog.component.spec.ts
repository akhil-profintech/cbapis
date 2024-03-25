jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { FundsTransferTransactionDetailsService } from '../service/funds-transfer-transaction-details.service';

import { FundsTransferTransactionDetailsDeleteDialogComponent } from './funds-transfer-transaction-details-delete-dialog.component';

describe('FundsTransferTransactionDetails Management Delete Component', () => {
  let comp: FundsTransferTransactionDetailsDeleteDialogComponent;
  let fixture: ComponentFixture<FundsTransferTransactionDetailsDeleteDialogComponent>;
  let service: FundsTransferTransactionDetailsService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, FundsTransferTransactionDetailsDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(FundsTransferTransactionDetailsDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FundsTransferTransactionDetailsDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FundsTransferTransactionDetailsService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
