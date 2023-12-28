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
import { ICreditAccountDetails } from '../credit-account-details.model';
import { CreditAccountDetailsService } from '../service/credit-account-details.service';
import { CreditAccountDetailsFormService } from './credit-account-details-form.service';

import { CreditAccountDetailsUpdateComponent } from './credit-account-details-update.component';

describe('CreditAccountDetails Management Update Component', () => {
  let comp: CreditAccountDetailsUpdateComponent;
  let fixture: ComponentFixture<CreditAccountDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let creditAccountDetailsFormService: CreditAccountDetailsFormService;
  let creditAccountDetailsService: CreditAccountDetailsService;
  let disbursementService: DisbursementService;
  let repaymentService: RepaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CreditAccountDetailsUpdateComponent],
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
      .overrideTemplate(CreditAccountDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreditAccountDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    creditAccountDetailsFormService = TestBed.inject(CreditAccountDetailsFormService);
    creditAccountDetailsService = TestBed.inject(CreditAccountDetailsService);
    disbursementService = TestBed.inject(DisbursementService);
    repaymentService = TestBed.inject(RepaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Disbursement query and add missing value', () => {
      const creditAccountDetails: ICreditAccountDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 15186 };
      creditAccountDetails.disbursement = disbursement;

      const disbursementCollection: IDisbursement[] = [{ id: 22419 }];
      jest.spyOn(disbursementService, 'query').mockReturnValue(of(new HttpResponse({ body: disbursementCollection })));
      const additionalDisbursements = [disbursement];
      const expectedCollection: IDisbursement[] = [...additionalDisbursements, ...disbursementCollection];
      jest.spyOn(disbursementService, 'addDisbursementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ creditAccountDetails });
      comp.ngOnInit();

      expect(disbursementService.query).toHaveBeenCalled();
      expect(disbursementService.addDisbursementToCollectionIfMissing).toHaveBeenCalledWith(
        disbursementCollection,
        ...additionalDisbursements.map(expect.objectContaining),
      );
      expect(comp.disbursementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Repayment query and add missing value', () => {
      const creditAccountDetails: ICreditAccountDetails = { id: 456 };
      const repayment: IRepayment = { id: 23979 };
      creditAccountDetails.repayment = repayment;

      const repaymentCollection: IRepayment[] = [{ id: 24799 }];
      jest.spyOn(repaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: repaymentCollection })));
      const additionalRepayments = [repayment];
      const expectedCollection: IRepayment[] = [...additionalRepayments, ...repaymentCollection];
      jest.spyOn(repaymentService, 'addRepaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ creditAccountDetails });
      comp.ngOnInit();

      expect(repaymentService.query).toHaveBeenCalled();
      expect(repaymentService.addRepaymentToCollectionIfMissing).toHaveBeenCalledWith(
        repaymentCollection,
        ...additionalRepayments.map(expect.objectContaining),
      );
      expect(comp.repaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const creditAccountDetails: ICreditAccountDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 18833 };
      creditAccountDetails.disbursement = disbursement;
      const repayment: IRepayment = { id: 30657 };
      creditAccountDetails.repayment = repayment;

      activatedRoute.data = of({ creditAccountDetails });
      comp.ngOnInit();

      expect(comp.disbursementsSharedCollection).toContain(disbursement);
      expect(comp.repaymentsSharedCollection).toContain(repayment);
      expect(comp.creditAccountDetails).toEqual(creditAccountDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreditAccountDetails>>();
      const creditAccountDetails = { id: 123 };
      jest.spyOn(creditAccountDetailsFormService, 'getCreditAccountDetails').mockReturnValue(creditAccountDetails);
      jest.spyOn(creditAccountDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creditAccountDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: creditAccountDetails }));
      saveSubject.complete();

      // THEN
      expect(creditAccountDetailsFormService.getCreditAccountDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(creditAccountDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(creditAccountDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreditAccountDetails>>();
      const creditAccountDetails = { id: 123 };
      jest.spyOn(creditAccountDetailsFormService, 'getCreditAccountDetails').mockReturnValue({ id: null });
      jest.spyOn(creditAccountDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creditAccountDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: creditAccountDetails }));
      saveSubject.complete();

      // THEN
      expect(creditAccountDetailsFormService.getCreditAccountDetails).toHaveBeenCalled();
      expect(creditAccountDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreditAccountDetails>>();
      const creditAccountDetails = { id: 123 };
      jest.spyOn(creditAccountDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creditAccountDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(creditAccountDetailsService.update).toHaveBeenCalled();
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
