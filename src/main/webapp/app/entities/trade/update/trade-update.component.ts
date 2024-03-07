import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { FinanceRequestService } from 'app/entities/finance-request/service/finance-request.service';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { ITradePartner } from 'app/entities/trade-partner/trade-partner.model';
import { TradePartnerService } from 'app/entities/trade-partner/service/trade-partner.service';
import { TradeService } from '../service/trade.service';
import { ITrade } from '../trade.model';
import { TradeFormService, TradeFormGroup } from './trade-form.service';

@Component({
  standalone: true,
  selector: 'jhi-trade-update',
  templateUrl: './trade-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TradeUpdateComponent implements OnInit {
  isSaving = false;
  trade: ITrade | null = null;

  financeRequestsSharedCollection: IFinanceRequest[] = [];
  anchorTradersSharedCollection: IAnchorTrader[] = [];
  tradePartnersSharedCollection: ITradePartner[] = [];

  editForm: TradeFormGroup = this.tradeFormService.createTradeFormGroup();

  constructor(
    protected tradeService: TradeService,
    protected tradeFormService: TradeFormService,
    protected financeRequestService: FinanceRequestService,
    protected anchorTraderService: AnchorTraderService,
    protected tradePartnerService: TradePartnerService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareFinanceRequest = (o1: IFinanceRequest | null, o2: IFinanceRequest | null): boolean =>
    this.financeRequestService.compareFinanceRequest(o1, o2);

  compareAnchorTrader = (o1: IAnchorTrader | null, o2: IAnchorTrader | null): boolean =>
    this.anchorTraderService.compareAnchorTrader(o1, o2);

  compareTradePartner = (o1: ITradePartner | null, o2: ITradePartner | null): boolean =>
    this.tradePartnerService.compareTradePartner(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trade }) => {
      this.trade = trade;
      if (trade) {
        this.updateForm(trade);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const trade = this.tradeFormService.getTrade(this.editForm);
    if (trade.id !== null) {
      this.subscribeToSaveResponse(this.tradeService.update(trade));
    } else {
      this.subscribeToSaveResponse(this.tradeService.create(trade));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrade>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(trade: ITrade): void {
    this.trade = trade;
    this.tradeFormService.resetForm(this.editForm, trade);

    this.financeRequestsSharedCollection = this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(
      this.financeRequestsSharedCollection,
      trade.financerequest,
    );
    this.anchorTradersSharedCollection = this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(
      this.anchorTradersSharedCollection,
      trade.anchortrader,
    );
    this.tradePartnersSharedCollection = this.tradePartnerService.addTradePartnerToCollectionIfMissing<ITradePartner>(
      this.tradePartnersSharedCollection,
      trade.tradepartner,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.financeRequestService
      .query()
      .pipe(map((res: HttpResponse<IFinanceRequest[]>) => res.body ?? []))
      .pipe(
        map((financeRequests: IFinanceRequest[]) =>
          this.financeRequestService.addFinanceRequestToCollectionIfMissing<IFinanceRequest>(financeRequests, this.trade?.financerequest),
        ),
      )
      .subscribe((financeRequests: IFinanceRequest[]) => (this.financeRequestsSharedCollection = financeRequests));

    this.anchorTraderService
      .query()
      .pipe(map((res: HttpResponse<IAnchorTrader[]>) => res.body ?? []))
      .pipe(
        map((anchorTraders: IAnchorTrader[]) =>
          this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(anchorTraders, this.trade?.anchortrader),
        ),
      )
      .subscribe((anchorTraders: IAnchorTrader[]) => (this.anchorTradersSharedCollection = anchorTraders));

    this.tradePartnerService
      .query()
      .pipe(map((res: HttpResponse<ITradePartner[]>) => res.body ?? []))
      .pipe(
        map((tradePartners: ITradePartner[]) =>
          this.tradePartnerService.addTradePartnerToCollectionIfMissing<ITradePartner>(tradePartners, this.trade?.tradepartner),
        ),
      )
      .subscribe((tradePartners: ITradePartner[]) => (this.tradePartnersSharedCollection = tradePartners));
  }
}
