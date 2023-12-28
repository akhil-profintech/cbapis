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
import { IEscrowAccountDetails } from '../escrow-account-details.model';
import { EscrowAccountDetailsService } from '../service/escrow-account-details.service';
import { EscrowAccountDetailsFormService } from './escrow-account-details-form.service';

import { EscrowAccountDetailsUpdateComponent } from './escrow-account-details-update.component';

describe('EscrowAccountDetails Management Update Component', () => {
  let comp: EscrowAccountDetailsUpdateComponent;
  let fixture: ComponentFixture<EscrowAccountDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let escrowAccountDetailsFormService: EscrowAccountDetailsFormService;
  let escrowAccountDetailsService: EscrowAccountDetailsService;
  let disbursementService: DisbursementService;
  let repaymentService: RepaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), EscrowAccountDetailsUpdateComponent],
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
      .overrideTemplate(EscrowAccountDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EscrowAccountDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    escrowAccountDetailsFormService = TestBed.inject(EscrowAccountDetailsFormService);
    escrowAccountDetailsService = TestBed.inject(EscrowAccountDetailsService);
    disbursementService = TestBed.inject(DisbursementService);
    repaymentService = TestBed.inject(RepaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Disbursement query and add missing value', () => {
      const escrowAccountDetails: IEscrowAccountDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 8459 };
      escrowAccountDetails.disbursement = disbursement;

      const disbursementCollection: IDisbursement[] = [{ id: 25875 }];
      jest.spyOn(disbursementService, 'query').mockReturnValue(of(new HttpResponse({ body: disbursementCollection })));
      const additionalDisbursements = [disbursement];
      const expectedCollection: IDisbursement[] = [...additionalDisbursements, ...disbursementCollection];
      jest.spyOn(disbursementService, 'addDisbursementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      expect(disbursementService.query).toHaveBeenCalled();
      expect(disbursementService.addDisbursementToCollectionIfMissing).toHaveBeenCalledWith(
        disbursementCollection,
        ...additionalDisbursements.map(expect.objectContaining),
      );
      expect(comp.disbursementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Repayment query and add missing value', () => {
      const escrowAccountDetails: IEscrowAccountDetails = { id: 456 };
      const repayment: IRepayment = { id: 10779 };
      escrowAccountDetails.repayment = repayment;

      const repaymentCollection: IRepayment[] = [{ id: 16308 }];
      jest.spyOn(repaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: repaymentCollection })));
      const additionalRepayments = [repayment];
      const expectedCollection: IRepayment[] = [...additionalRepayments, ...repaymentCollection];
      jest.spyOn(repaymentService, 'addRepaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      expect(repaymentService.query).toHaveBeenCalled();
      expect(repaymentService.addRepaymentToCollectionIfMissing).toHaveBeenCalledWith(
        repaymentCollection,
        ...additionalRepayments.map(expect.objectContaining),
      );
      expect(comp.repaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const escrowAccountDetails: IEscrowAccountDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 11398 };
      escrowAccountDetails.disbursement = disbursement;
      const repayment: IRepayment = { id: 21715 };
      escrowAccountDetails.repayment = repayment;

      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      expect(comp.disbursementsSharedCollection).toContain(disbursement);
      expect(comp.repaymentsSharedCollection).toContain(repayment);
      expect(comp.escrowAccountDetails).toEqual(escrowAccountDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowAccountDetails>>();
      const escrowAccountDetails = { id: 123 };
      jest.spyOn(escrowAccountDetailsFormService, 'getEscrowAccountDetails').mockReturnValue(escrowAccountDetails);
      jest.spyOn(escrowAccountDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: escrowAccountDetails }));
      saveSubject.complete();

      // THEN
      expect(escrowAccountDetailsFormService.getEscrowAccountDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(escrowAccountDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(escrowAccountDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowAccountDetails>>();
      const escrowAccountDetails = { id: 123 };
      jest.spyOn(escrowAccountDetailsFormService, 'getEscrowAccountDetails').mockReturnValue({ id: null });
      jest.spyOn(escrowAccountDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowAccountDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: escrowAccountDetails }));
      saveSubject.complete();

      // THEN
      expect(escrowAccountDetailsFormService.getEscrowAccountDetails).toHaveBeenCalled();
      expect(escrowAccountDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowAccountDetails>>();
      const escrowAccountDetails = { id: 123 };
      jest.spyOn(escrowAccountDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(escrowAccountDetailsService.update).toHaveBeenCalled();
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
