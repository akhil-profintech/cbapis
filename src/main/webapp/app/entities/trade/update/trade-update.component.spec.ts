import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { ITradePartner } from 'app/entities/trade-partner/trade-partner.model';
import { TradePartnerService } from 'app/entities/trade-partner/service/trade-partner.service';
import { ITrade } from '../trade.model';
import { TradeService } from '../service/trade.service';
import { TradeFormService } from './trade-form.service';

import { TradeUpdateComponent } from './trade-update.component';

describe('Trade Management Update Component', () => {
  let comp: TradeUpdateComponent;
  let fixture: ComponentFixture<TradeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tradeFormService: TradeFormService;
  let tradeService: TradeService;
  let financeRequestService: FinanceRequestService;
  let anchorTraderService: AnchorTraderService;
  let tradePartnerService: TradePartnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), TradeUpdateComponent],
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
      .overrideTemplate(TradeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TradeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tradeFormService = TestBed.inject(TradeFormService);
    tradeService = TestBed.inject(TradeService);
    financeRequestService = TestBed.inject(FinanceRequestService);
    anchorTraderService = TestBed.inject(AnchorTraderService);
    tradePartnerService = TestBed.inject(TradePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call FinanceRequest query and add missing value', () => {
      const trade: ITrade = { id: 456 };
      const financerequest: IFinanceRequest = { id: 12577 };
      trade.financerequest = financerequest;

      const financeRequestCollection: IFinanceRequest[] = [{ id: 11708 }];
      jest.spyOn(financeRequestService, 'query').mockReturnValue(of(new HttpResponse({ body: financeRequestCollection })));
      const additionalFinanceRequests = [financerequest];
      const expectedCollection: IFinanceRequest[] = [...additionalFinanceRequests, ...financeRequestCollection];
      jest.spyOn(financeRequestService, 'addFinanceRequestToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ trade });
      comp.ngOnInit();

      expect(financeRequestService.query).toHaveBeenCalled();
      expect(financeRequestService.addFinanceRequestToCollectionIfMissing).toHaveBeenCalledWith(
        financeRequestCollection,
        ...additionalFinanceRequests.map(expect.objectContaining),
      );
      expect(comp.financeRequestsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call AnchorTrader query and add missing value', () => {
      const trade: ITrade = { id: 456 };
      const anchortrader: IAnchorTrader = { id: 4349 };
      trade.anchortrader = anchortrader;

      const anchorTraderCollection: IAnchorTrader[] = [{ id: 2707 }];
      jest.spyOn(anchorTraderService, 'query').mockReturnValue(of(new HttpResponse({ body: anchorTraderCollection })));
      const additionalAnchorTraders = [anchortrader];
      const expectedCollection: IAnchorTrader[] = [...additionalAnchorTraders, ...anchorTraderCollection];
      jest.spyOn(anchorTraderService, 'addAnchorTraderToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ trade });
      comp.ngOnInit();

      expect(anchorTraderService.query).toHaveBeenCalled();
      expect(anchorTraderService.addAnchorTraderToCollectionIfMissing).toHaveBeenCalledWith(
        anchorTraderCollection,
        ...additionalAnchorTraders.map(expect.objectContaining),
      );
      expect(comp.anchorTradersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call TradePartner query and add missing value', () => {
      const trade: ITrade = { id: 456 };
      const tradepartner: ITradePartner = { id: 11660 };
      trade.tradepartner = tradepartner;

      const tradePartnerCollection: ITradePartner[] = [{ id: 20970 }];
      jest.spyOn(tradePartnerService, 'query').mockReturnValue(of(new HttpResponse({ body: tradePartnerCollection })));
      const additionalTradePartners = [tradepartner];
      const expectedCollection: ITradePartner[] = [...additionalTradePartners, ...tradePartnerCollection];
      jest.spyOn(tradePartnerService, 'addTradePartnerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ trade });
      comp.ngOnInit();

      expect(tradePartnerService.query).toHaveBeenCalled();
      expect(tradePartnerService.addTradePartnerToCollectionIfMissing).toHaveBeenCalledWith(
        tradePartnerCollection,
        ...additionalTradePartners.map(expect.objectContaining),
      );
      expect(comp.tradePartnersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const trade: ITrade = { id: 456 };
      const financerequest: IFinanceRequest = { id: 8337 };
      trade.financerequest = financerequest;
      const anchortrader: IAnchorTrader = { id: 4522 };
      trade.anchortrader = anchortrader;
      const tradepartner: ITradePartner = { id: 15298 };
      trade.tradepartner = tradepartner;

      activatedRoute.data = of({ trade });
      comp.ngOnInit();

      expect(comp.financeRequestsSharedCollection).toContain(financerequest);
      expect(comp.anchorTradersSharedCollection).toContain(anchortrader);
      expect(comp.tradePartnersSharedCollection).toContain(tradepartner);
      expect(comp.trade).toEqual(trade);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrade>>();
      const trade = { id: 123 };
      jest.spyOn(tradeFormService, 'getTrade').mockReturnValue(trade);
      jest.spyOn(tradeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trade }));
      saveSubject.complete();

      // THEN
      expect(tradeFormService.getTrade).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tradeService.update).toHaveBeenCalledWith(expect.objectContaining(trade));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrade>>();
      const trade = { id: 123 };
      jest.spyOn(tradeFormService, 'getTrade').mockReturnValue({ id: null });
      jest.spyOn(tradeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trade: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trade }));
      saveSubject.complete();

      // THEN
      expect(tradeFormService.getTrade).toHaveBeenCalled();
      expect(tradeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrade>>();
      const trade = { id: 123 };
      jest.spyOn(tradeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tradeService.update).toHaveBeenCalled();
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

    describe('compareAnchorTrader', () => {
      it('Should forward to anchorTraderService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(anchorTraderService, 'compareAnchorTrader');
        comp.compareAnchorTrader(entity, entity2);
        expect(anchorTraderService.compareAnchorTrader).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareTradePartner', () => {
      it('Should forward to tradePartnerService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(tradePartnerService, 'compareTradePartner');
        comp.compareTradePartner(entity, entity2);
        expect(tradePartnerService.compareTradePartner).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
