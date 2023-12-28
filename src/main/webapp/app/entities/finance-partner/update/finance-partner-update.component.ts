import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinancePartner } from '../finance-partner.model';
import { FinancePartnerService } from '../service/finance-partner.service';
import { FinancePartnerFormService, FinancePartnerFormGroup } from './finance-partner-form.service';

@Component({
  standalone: true,
  selector: 'jhi-finance-partner-update',
  templateUrl: './finance-partner-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FinancePartnerUpdateComponent implements OnInit {
  isSaving = false;
  financePartner: IFinancePartner | null = null;

  editForm: FinancePartnerFormGroup = this.financePartnerFormService.createFinancePartnerFormGroup();

  constructor(
    protected financePartnerService: FinancePartnerService,
    protected financePartnerFormService: FinancePartnerFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ financePartner }) => {
      this.financePartner = financePartner;
      if (financePartner) {
        this.updateForm(financePartner);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const financePartner = this.financePartnerFormService.getFinancePartner(this.editForm);
    if (financePartner.id !== null) {
      this.subscribeToSaveResponse(this.financePartnerService.update(financePartner));
    } else {
      this.subscribeToSaveResponse(this.financePartnerService.create(financePartner));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinancePartner>>): void {
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

  protected updateForm(financePartner: IFinancePartner): void {
    this.financePartner = financePartner;
    this.financePartnerFormService.resetForm(this.editForm, financePartner);
  }
}
