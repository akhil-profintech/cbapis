import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { DisbursementService } from 'app/entities/disbursement/service/disbursement.service';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { RepaymentService } from 'app/entities/repayment/service/repayment.service';
import { IEscrowTransactionDetails } from '../escrow-transaction-details.model';
import { EscrowTransactionDetailsService } from '../service/escrow-transaction-details.service';
import { EscrowTransactionDetailsFormService } from './escrow-transaction-details-form.service';

import { EscrowTransactionDetailsUpdateComponent } from './escrow-transaction-details-update.component';

describe('EscrowTransactionDetails Management Update Component', () => {
  let comp: EscrowTransactionDetailsUpdateComponent;
  let fixture: ComponentFixture<EscrowTransactionDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let escrowTransactionDetailsFormService: EscrowTransactionDetailsFormService;
  let escrowTransactionDetailsService: EscrowTransactionDetailsService;
  let disbursementService: DisbursementService;
  let repaymentService: RepaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), EscrowTransactionDetailsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(EscrowTransactionDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EscrowTransactionDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    escrowTransactionDetailsFormService = TestBed.inject(EscrowTransactionDetailsFormService);
    escrowTransactionDetailsService = TestBed.inject(EscrowTransactionDetailsService);
    disbursementService = TestBed.inject(DisbursementService);
    repaymentService = TestBed.inject(RepaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Disbursement query and add missing value', () => {
      const escrowTransactionDetails: IEscrowTransactionDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 28741 };
      escrowTransactionDetails.disbursement = disbursement;

      const disbursementCollection: IDisbursement[] = [{ id: 21769 }];
      jest.spyOn(disbursementService, 'query').mockReturnValue(of(new HttpResponse({ body: disbursementCollection })));
      const additionalDisbursements = [disbursement];
      const expectedCollection: IDisbursement[] = [...additionalDisbursements, ...disbursementCollection];
      jest.spyOn(disbursementService, 'addDisbursementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ escrowTransactionDetails });
      comp.ngOnInit();

      expect(disbursementService.query).toHaveBeenCalled();
      expect(disbursementService.addDisbursementToCollectionIfMissing).toHaveBeenCalledWith(
        disbursementCollection,
        ...additionalDisbursements.map(expect.objectContaining),
      );
      expect(comp.disbursementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Repayment query and add missing value', () => {
      const escrowTransactionDetails: IEscrowTransactionDetails = { id: 456 };
      const repayment: IRepayment = { id: 9382 };
      escrowTransactionDetails.repayment = repayment;

      const repaymentCollection: IRepayment[] = [{ id: 31266 }];
      jest.spyOn(repaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: repaymentCollection })));
      const additionalRepayments = [repayment];
      const expectedCollection: IRepayment[] = [...additionalRepayments, ...repaymentCollection];
      jest.spyOn(repaymentService, 'addRepaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ escrowTransactionDetails });
      comp.ngOnInit();

      expect(repaymentService.query).toHaveBeenCalled();
      expect(repaymentService.addRepaymentToCollectionIfMissing).toHaveBeenCalledWith(
        repaymentCollection,
        ...additionalRepayments.map(expect.objectContaining),
      );
      expect(comp.repaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const escrowTransactionDetails: IEscrowTransactionDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 1726 };
      escrowTransactionDetails.disbursement = disbursement;
      const repayment: IRepayment = { id: 20423 };
      escrowTransactionDetails.repayment = repayment;

      activatedRoute.data = of({ escrowTransactionDetails });
      comp.ngOnInit();

      expect(comp.disbursementsSharedCollection).toContain(disbursement);
      expect(comp.repaymentsSharedCollection).toContain(repayment);
      expect(comp.escrowTransactionDetails).toEqual(escrowTransactionDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowTransactionDetails>>();
      const escrowTransactionDetails = { id: 123 };
      jest.spyOn(escrowTransactionDetailsFormService, 'getEscrowTransactionDetails').mockReturnValue(escrowTransactionDetails);
      jest.spyOn(escrowTransactionDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowTransactionDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: escrowTransactionDetails }));
      saveSubject.complete();

      // THEN
      expect(escrowTransactionDetailsFormService.getEscrowTransactionDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(escrowTransactionDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(escrowTransactionDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowTransactionDetails>>();
      const escrowTransactionDetails = { id: 123 };
      jest.spyOn(escrowTransactionDetailsFormService, 'getEscrowTransactionDetails').mockReturnValue({ id: null });
      jest.spyOn(escrowTransactionDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowTransactionDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: escrowTransactionDetails }));
      saveSubject.complete();

      // THEN
      expect(escrowTransactionDetailsFormService.getEscrowTransactionDetails).toHaveBeenCalled();
      expect(escrowTransactionDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowTransactionDetails>>();
      const escrowTransactionDetails = { id: 123 };
      jest.spyOn(escrowTransactionDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowTransactionDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(escrowTransactionDetailsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDisbursement', () => {
      it('Should forward to disbursementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(disbursementService, 'compareDisbursement');
        comp.compareDisbursement(entity, entity2);
        expect(disbursementService.compareDisbursement).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRepayment', () => {
      it('Should forward to repaymentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(repaymentService, 'compareRepayment');
        comp.compareRepayment(entity, entity2);
        expect(repaymentService.compareRepayment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
