import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { IFinanceRequest } from '../finance-request.model';
import { FinanceRequestService } from '../service/finance-request.service';
import { FinanceRequestFormService, FinanceRequestFormGroup } from './finance-request-form.service';

@Component({
  standalone: true,
  selector: 'jhi-finance-request-update',
  templateUrl: './finance-request-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FinanceRequestUpdateComponent implements OnInit {
  isSaving = false;
  financeRequest: IFinanceRequest | null = null;

  anchorTradersSharedCollection: IAnchorTrader[] = [];

  editForm: FinanceRequestFormGroup = this.financeRequestFormService.createFinanceRequestFormGroup();

  constructor(
    protected financeRequestService: FinanceRequestService,
    protected financeRequestFormService: FinanceRequestFormService,
    protected anchorTraderService: AnchorTraderService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareAnchorTrader = (o1: IAnchorTrader | null, o2: IAnchorTrader | null): boolean =>
    this.anchorTraderService.compareAnchorTrader(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ financeRequest }) => {
      this.financeRequest = financeRequest;
      if (financeRequest) {
        this.updateForm(financeRequest);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const financeRequest = this.financeRequestFormService.getFinanceRequest(this.editForm);
    if (financeRequest.id !== null) {
      this.subscribeToSaveResponse(this.financeRequestService.update(financeRequest));
    } else {
      this.subscribeToSaveResponse(this.financeRequestService.create(financeRequest));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinanceRequest>>): void {
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

  protected updateForm(financeRequest: IFinanceRequest): void {
    this.financeRequest = financeRequest;
    this.financeRequestFormService.resetForm(this.editForm, financeRequest);

    this.anchorTradersSharedCollection = this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(
      this.anchorTradersSharedCollection,
      financeRequest.anchortrader,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.anchorTraderService
      .query()
      .pipe(map((res: HttpResponse<IAnchorTrader[]>) => res.body ?? []))
      .pipe(
        map((anchorTraders: IAnchorTrader[]) =>
          this.anchorTraderService.addAnchorTraderToCollectionIfMissing<IAnchorTrader>(anchorTraders, this.financeRequest?.anchortrader),
        ),
      )
      .subscribe((anchorTraders: IAnchorTrader[]) => (this.anchorTradersSharedCollection = anchorTraders));
  }
}
