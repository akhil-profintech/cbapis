import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradePartner } from '../trade-partner.model';
import { TradePartnerService } from '../service/trade-partner.service';
import { TradePartnerFormService, TradePartnerFormGroup } from './trade-partner-form.service';

@Component({
  standalone: true,
  selector: 'jhi-trade-partner-update',
  templateUrl: './trade-partner-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TradePartnerUpdateComponent implements OnInit {
  isSaving = false;
  tradePartner: ITradePartner | null = null;

  editForm: TradePartnerFormGroup = this.tradePartnerFormService.createTradePartnerFormGroup();

  constructor(
    protected tradePartnerService: TradePartnerService,
    protected tradePartnerFormService: TradePartnerFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tradePartner }) => {
      this.tradePartner = tradePartner;
      if (tradePartner) {
        this.updateForm(tradePartner);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tradePartner = this.tradePartnerFormService.getTradePartner(this.editForm);
    if (tradePartner.id !== null) {
      this.subscribeToSaveResponse(this.tradePartnerService.update(tradePartner));
    } else {
      this.subscribeToSaveResponse(this.tradePartnerService.create(tradePartner));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITradePartner>>): void {
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

  protected updateForm(tradePartner: ITradePartner): void {
    this.tradePartner = tradePartner;
    this.tradePartnerFormService.resetForm(this.editForm, tradePartner);
  }
}
