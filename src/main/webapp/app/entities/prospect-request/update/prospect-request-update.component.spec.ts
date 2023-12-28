import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { ProspectRequestService } from '../service/prospect-request.service';
import { IProspectRequest } from '../prospect-request.model';
import { ProspectRequestFormService } from './prospect-request-form.service';

import { ProspectRequestUpdateComponent } from './prospect-request-update.component';

describe('ProspectRequest Management Update Component', () => {
  let comp: ProspectRequestUpdateComponent;
  let fixture: ComponentFixture<ProspectRequestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let prospectRequestFormService: ProspectRequestFormService;
  let prospectRequestService: ProspectRequestService;
  let financeRequestService: FinanceRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ProspectRequestUpdateComponent],
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
      .overrideTemplate(ProspectRequestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProspectRequestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    prospectRequestFormService = TestBed.inject(ProspectRequestFormService);
    prospectRequestService = TestBed.inject(ProspectRequestService);
    financeRequestService = TestBed.inject(FinanceRequestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const prospectRequest: IProspectRequest = { id: 456 };
      const financerequest: IFinanceRequest = { id: 10319 };
      prospectRequest.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 18721 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const prospectRequest: IProspectRequest = { id: 456 };
      const financerequest: IFinanceRequest = { id: 24636 };
      prospectRequest.financerequest = financerequest;

      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.prospectRequest).toEqual(prospectRequest);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProspectRequest>>();
      const prospectRequest = { id: 123 };
      jest.spyOn(prospectRequestFormService, 'getProspectRequest').mockReturnValue(prospectRequest);
      jest.spyOn(prospectRequestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: prospectRequest }));
      saveSubject.complete();

      // THEN
      expect(prospectRequestFormService.getProspectRequest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(prospectRequestService.update).toHaveBeenCalledWith(expect.objectContaining(prospectRequest));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProspectRequest>>();
      const prospectRequest = { id: 123 };
      jest.spyOn(prospectRequestFormService, 'getProspectRequest').mockReturnValue({ id: null });
      jest.spyOn(prospectRequestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ prospectRequest: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: prospectRequest }));
      saveSubject.complete();

      // THEN
      expect(prospectRequestFormService.getProspectRequest).toHaveBeenCalled();
      expect(prospectRequestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProspectRequest>>();
      const prospectRequest = { id: 123 };
      jest.spyOn(prospectRequestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(prospectRequestService.update).toHaveBeenCalled();
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
  });
});
