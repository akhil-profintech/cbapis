import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { DocDetailsService } from '../service/doc-details.service';
import { IDocDetails } from '../doc-details.model';
import { DocDetailsFormService } from './doc-details-form.service';

import { DocDetailsUpdateComponent } from './doc-details-update.component';

describe('DocDetails Management Update Component', () => {
  let comp: DocDetailsUpdateComponent;
  let fixture: ComponentFixture<DocDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let docDetailsFormService: DocDetailsFormService;
  let docDetailsService: DocDetailsService;
  let financeRequestService: FinanceRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DocDetailsUpdateComponent],
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
      .overrideTemplate(DocDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DocDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    docDetailsFormService = TestBed.inject(DocDetailsFormService);
    docDetailsService = TestBed.inject(DocDetailsService);
    financeRequestService = TestBed.inject(FinanceRequestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const docDetails: IDocDetails = { id: 456 };
      const financeRequest: IFinanceRequest = { id: 26247 };
      docDetails.financeRequest = financeRequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 22413 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financeRequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const docDetails: IDocDetails = { id: 456 };
      const financeRequest: IFinanceRequest = { id: 25925 };
      docDetails.financeRequest = financeRequest;

      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financeRequest);
      expect(comp.docDetails).toEqual(docDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocDetails>>();
      const docDetails = { id: 123 };
      jest.spyOn(docDetailsFormService, 'getDocDetails').mockReturnValue(docDetails);
      jest.spyOn(docDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: docDetails }));
      saveSubject.complete();

      // THEN
      expect(docDetailsFormService.getDocDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(docDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(docDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocDetails>>();
      const docDetails = { id: 123 };
      jest.spyOn(docDetailsFormService, 'getDocDetails').mockReturnValue({ id: null });
      jest.spyOn(docDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ docDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: docDetails }));
      saveSubject.complete();

      // THEN
      expect(docDetailsFormService.getDocDetails).toHaveBeenCalled();
      expect(docDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocDetails>>();
      const docDetails = { id: 123 };
      jest.spyOn(docDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(docDetailsService.update).toHaveBeenCalled();
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
