import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { FinanceRequestService } from '../service/finance-request.service';
import { IFinanceRequest } from '../finance-request.model';
import { FinanceRequestFormService } from './finance-request-form.service';

import { FinanceRequestUpdateComponent } from './finance-request-update.component';

describe('FinanceRequest Management Update Component', () => {
  let comp: FinanceRequestUpdateComponent;
  let fixture: ComponentFixture<FinanceRequestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let financeRequestFormService: FinanceRequestFormService;
  let financeRequestService: FinanceRequestService;
  let anchorTraderService: AnchorTraderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FinanceRequestUpdateComponent],
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
      .overrideTemplate(FinanceRequestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FinanceRequestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    financeRequestFormService = TestBed.inject(FinanceRequestFormService);
    financeRequestService = TestBed.inject(FinanceRequestService);
    anchorTraderService = TestBed.inject(AnchorTraderService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call AnchorTrader query and add missing value', () => {
      const financeRequest: IFinanceRequest = { id: 456 };
      const anchortrader: IAnchorTrader = { id: 30230 };
      financeRequest.anchortrader = anchortrader;

      const anchorTraderCollection: IAnchorTrader[] = [{ id: 7471 }];
      jest.spyOn(anchorTraderService, 'query').mockReturnValue(of(new HttpResponse({ body: anchorTraderCollection })));
      const additionalAnchorTraders = [anchortrader];
      const expectedCollection: IAnchorTrader[] = [...additionalAnchorTraders, ...anchorTraderCollection];
      jest.spyOn(anchorTraderService, 'addAnchorTraderToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ financeRequest });
      comp.ngOnInit();

      expect(anchorTraderService.query).toHaveBeenCalled();
      expect(anchorTraderService.addAnchorTraderToCollectionIfMissing).toHaveBeenCalledWith(
        anchorTraderCollection,
        ...additionalAnchorTraders.map(expect.objectContaining),
      );
      expect(comp.anchorTradersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const financeRequest: IFinanceRequest = { id: 456 };
      const anchortrader: IAnchorTrader = { id: 23107 };
      financeRequest.anchortrader = anchortrader;

      activatedRoute.data = of({ financeRequest });
      comp.ngOnInit();

      expect(comp.anchorTradersSharedCollection).toContain(anchortrader);
      expect(comp.financeRequest).toEqual(financeRequest);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinanceRequest>>();
      const financeRequest = { id: 123 };
      jest.spyOn(financeRequestFormService, 'getFinanceRequest').mockReturnValue(financeRequest);
      jest.spyOn(financeRequestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financeRequest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: financeRequest }));
      saveSubject.complete();

      // THEN
      expect(financeRequestFormService.getFinanceRequest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(financeRequestService.update).toHaveBeenCalledWith(expect.objectContaining(financeRequest));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinanceRequest>>();
      const financeRequest = { id: 123 };
      jest.spyOn(financeRequestFormService, 'getFinanceRequest').mockReturnValue({ id: null });
      jest.spyOn(financeRequestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financeRequest: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: financeRequest }));
      saveSubject.complete();

      // THEN
      expect(financeRequestFormService.getFinanceRequest).toHaveBeenCalled();
      expect(financeRequestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinanceRequest>>();
      const financeRequest = { id: 123 };
      jest.spyOn(financeRequestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financeRequest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(financeRequestService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAnchorTrader', () => {
      it('Should forward to anchorTraderService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(anchorTraderService, 'compareAnchorTrader');
        comp.compareAnchorTrader(entity, entity2);
        expect(anchorTraderService.compareAnchorTrader).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
