import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';
import { FinancePartnerService } from 'app/entities/finance-partner/service/finance-partner.service';
import { IDisbursement } from '../disbursement.model';
import { DisbursementService } from '../service/disbursement.service';
import { DisbursementFormService } from './disbursement-form.service';

import { DisbursementUpdateComponent } from './disbursement-update.component';

describe('Disbursement Management Update Component', () => {
  let comp: DisbursementUpdateComponent;
  let fixture: ComponentFixture<DisbursementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let disbursementFormService: DisbursementFormService;
  let disbursementService: DisbursementService;
  let financeRequestService: FinanceRequestService;
  let financePartnerService: FinancePartnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DisbursementUpdateComponent],
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
      .overrideTemplate(DisbursementUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DisbursementUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    disbursementFormService = TestBed.inject(DisbursementFormService);
    disbursementService = TestBed.inject(DisbursementService);
    financeRequestService = TestBed.inject(FinanceRequestService);
    financePartnerService = TestBed.inject(FinancePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const disbursement: IDisbursement = { id: 456 };
      const financerequest: IFinanceRequest = { id: 12405 };
      disbursement.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 23884 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ disbursement });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call FinancePartner query and add missing value', () => {
      const disbursement: IDisbursement = { id: 456 };
      const financepartner: IFinancePartner = { id: 19193 };
      disbursement.financepartner = financepartner;

      const financePartnerCollection: IFinancePartner[] = [{ id: 23554 }];
      jest.spyOn(financePartnerService, 'query').mockReturnValue(of(new HttpResponse({ body: financePartnerCollection })));
      const additionalFinancePartners = [financepartner];
      const expectedCollection: IFinancePartner[] = [...additionalFinancePartners, ...financePartnerCollection];
      jest.spyOn(financePartnerService, 'addFinancePartnerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ disbursement });
      comp.ngOnInit();

      expect(financePartnerService.query).toHaveBeenCalled();
      expect(financePartnerService.addFinancePartnerToCollectionIfMissing).toHaveBeenCalledWith(
        financePartnerCollection,
        ...additionalFinancePartners.map(expect.objectContaining),
      );
      expect(comp.financePartnersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const disbursement: IDisbursement = { id: 456 };
      const financerequest: IFinanceRequest = { id: 15400 };
      disbursement.financerequest = financerequest;
      const financepartner: IFinancePartner = { id: 13469 };
      disbursement.financepartner = financepartner;

      activatedRoute.data = of({ disbursement });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.financePartnersSharedCollection).toContain(financepartner);
      expect(comp.disbursement).toEqual(disbursement);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisbursement>>();
      const disbursement = { id: 123 };
      jest.spyOn(disbursementFormService, 'getDisbursement').mockReturnValue(disbursement);
      jest.spyOn(disbursementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disbursement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: disbursement }));
      saveSubject.complete();

      // THEN
      expect(disbursementFormService.getDisbursement).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(disbursementService.update).toHaveBeenCalledWith(expect.objectContaining(disbursement));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisbursement>>();
      const disbursement = { id: 123 };
      jest.spyOn(disbursementFormService, 'getDisbursement').mockReturnValue({ id: null });
      jest.spyOn(disbursementService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disbursement: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: disbursement }));
      saveSubject.complete();

      // THEN
      expect(disbursementFormService.getDisbursement).toHaveBeenCalled();
      expect(disbursementService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisbursement>>();
      const disbursement = { id: 123 };
      jest.spyOn(disbursementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ disbursement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(disbursementService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareFinanceRequest', () => {
      it('Should forward to financeRequestService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(financeRequestService, 'compareFinanceRequest');
        comp.compareFinanceRequest(entity, entity2);
        expect(financeRequestService.compareFinanceRequest).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareFinancePartner', () => {
      it('Should forward to financePartnerService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(financePartnerService, 'compareFinancePartner');
        comp.compareFinancePartner(entity, entity2);
        expect(financePartnerService.compareFinancePartner).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
