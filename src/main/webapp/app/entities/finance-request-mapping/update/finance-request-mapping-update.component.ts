import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IFinanceRequestMapping } from '../finance-request-mapping.model';
import { FinanceRequestMappingService } from '../service/finance-request-mapping.service';
import { FinanceRequestMappingFormService, FinanceRequestMappingFormGroup } from './finance-request-mapping-form.service';

@Component({
  standalone: true,
  selector: 'jhi-finance-request-mapping-update',
  templateUrl: './finance-request-mapping-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FinanceRequestMappingUpdateComponent implements OnInit {
  isSaving = false;
  financeRequestMapping: IFinanceRequestMapping | null = null;

  editForm: FinanceRequestMappingFormGroup = this.financeRequestMappingFormService.createFinanceRequestMappingFormGroup();

  constructor(
    protected financeRequestMappingService: FinanceRequestMappingService,
    protected financeRequestMappingFormService: FinanceRequestMappingFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ financeRequestMapping }) => {
      this.financeRequestMapping = financeRequestMapping;
      if (financeRequestMapping) {
        this.updateForm(financeRequestMapping);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const financeRequestMapping = this.financeRequestMappingFormService.getFinanceRequestMapping(this.editForm);
    if (financeRequestMapping.id !== null) {
      this.subscribeToSaveResponse(this.financeRequestMappingService.update(financeRequestMapping));
    } else {
      this.subscribeToSaveResponse(this.financeRequestMappingService.create(financeRequestMapping));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinanceRequestMapping>>): void {
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

  protected updateForm(financeRequestMapping: IFinanceRequestMapping): void {
    this.financeRequestMapping = financeRequestMapping;
    this.financeRequestMappingFormService.resetForm(this.editForm, financeRequestMapping);
  }
}
