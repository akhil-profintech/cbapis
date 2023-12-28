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
import { ICollectionTransactionDetails } from '../collection-transaction-details.model';
import { CollectionTransactionDetailsService } from '../service/collection-transaction-details.service';
import { CollectionTransactionDetailsFormService } from './collection-transaction-details-form.service';

import { CollectionTransactionDetailsUpdateComponent } from './collection-transaction-details-update.component';

describe('CollectionTransactionDetails Management Update Component', () => {
  let comp: CollectionTransactionDetailsUpdateComponent;
  let fixture: ComponentFixture<CollectionTransactionDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let collectionTransactionDetailsFormService: CollectionTransactionDetailsFormService;
  let collectionTransactionDetailsService: CollectionTransactionDetailsService;
  let disbursementService: DisbursementService;
  let repaymentService: RepaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CollectionTransactionDetailsUpdateComponent],
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
      .overrideTemplate(CollectionTransactionDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CollectionTransactionDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    collectionTransactionDetailsFormService = TestBed.inject(CollectionTransactionDetailsFormService);
    collectionTransactionDetailsService = TestBed.inject(CollectionTransactionDetailsService);
    disbursementService = TestBed.inject(DisbursementService);
    repaymentService = TestBed.inject(RepaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Disbursement query and add missing value', () => {
      const collectionTransactionDetails: ICollectionTransactionDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 11188 };
      collectionTransactionDetails.disbursement = disbursement;

      const disbursementCollection: IDisbursement[] = [{ id: 73 }];
      jest.spyOn(disbursementService, 'query').mockReturnValue(of(new HttpResponse({ body: disbursementCollection })));
      const additionalDisbursements = [disbursement];
      const expectedCollection: IDisbursement[] = [...additionalDisbursements, ...disbursementCollection];
      jest.spyOn(disbursementService, 'addDisbursementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ collectionTransactionDetails });
      comp.ngOnInit();

      expect(disbursementService.query).toHaveBeenCalled();
      expect(disbursementService.addDisbursementToCollectionIfMissing).toHaveBeenCalledWith(
        disbursementCollection,
        ...additionalDisbursements.map(expect.objectContaining),
      );
      expect(comp.disbursementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Repayment query and add missing value', () => {
      const collectionTransactionDetails: ICollectionTransactionDetails = { id: 456 };
      const repayment: IRepayment = { id: 22722 };
      collectionTransactionDetails.repayment = repayment;

      const repaymentCollection: IRepayment[] = [{ id: 7524 }];
      jest.spyOn(repaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: repaymentCollection })));
      const additionalRepayments = [repayment];
      const expectedCollection: IRepayment[] = [...additionalRepayments, ...repaymentCollection];
      jest.spyOn(repaymentService, 'addRepaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ collectionTransactionDetails });
      comp.ngOnInit();

      expect(repaymentService.query).toHaveBeenCalled();
      expect(repaymentService.addRepaymentToCollectionIfMissing).toHaveBeenCalledWith(
        repaymentCollection,
        ...additionalRepayments.map(expect.objectContaining),
      );
      expect(comp.repaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const collectionTransactionDetails: ICollectionTransactionDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 29785 };
      collectionTransactionDetails.disbursement = disbursement;
      const repayment: IRepayment = { id: 14131 };
      collectionTransactionDetails.repayment = repayment;

      activatedRoute.data = of({ collectionTransactionDetails });
      comp.ngOnInit();

      expect(comp.disbursementsSharedCollection).toContain(disbursement);
      expect(comp.repaymentsSharedCollection).toContain(repayment);
      expect(comp.collectionTransactionDetails).toEqual(collectionTransactionDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICollectionTransactionDetails>>();
      const collectionTransactionDetails = { id: 123 };
      jest.spyOn(collectionTransactionDetailsFormService, 'getCollectionTransactionDetails').mockReturnValue(collectionTransactionDetails);
      jest.spyOn(collectionTransactionDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ collectionTransactionDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: collectionTransactionDetails }));
      saveSubject.complete();

      // THEN
      expect(collectionTransactionDetailsFormService.getCollectionTransactionDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(collectionTransactionDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(collectionTransactionDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICollectionTransactionDetails>>();
      const collectionTransactionDetails = { id: 123 };
      jest.spyOn(collectionTransactionDetailsFormService, 'getCollectionTransactionDetails').mockReturnValue({ id: null });
      jest.spyOn(collectionTransactionDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ collectionTransactionDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: collectionTransactionDetails }));
      saveSubject.complete();

      // THEN
      expect(collectionTransactionDetailsFormService.getCollectionTransactionDetails).toHaveBeenCalled();
      expect(collectionTransactionDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICollectionTransactionDetails>>();
      const collectionTransactionDetails = { id: 123 };
      jest.spyOn(collectionTransactionDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ collectionTransactionDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(collectionTransactionDetailsService.update).toHaveBeenCalled();
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
