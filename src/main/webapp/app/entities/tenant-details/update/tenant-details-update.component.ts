import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITenantDetails } from '../tenant-details.model';
import { TenantDetailsService } from '../service/tenant-details.service';
import { TenantDetailsFormService, TenantDetailsFormGroup } from './tenant-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-tenant-details-update',
  templateUrl: './tenant-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TenantDetailsUpdateComponent implements OnInit {
  isSaving = false;
  tenantDetails: ITenantDetails | null = null;

  editForm: TenantDetailsFormGroup = this.tenantDetailsFormService.createTenantDetailsFormGroup();

  constructor(
    protected tenantDetailsService: TenantDetailsService,
    protected tenantDetailsFormService: TenantDetailsFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tenantDetails }) => {
      this.tenantDetails = tenantDetails;
      if (tenantDetails) {
        this.updateForm(tenantDetails);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tenantDetails = this.tenantDetailsFormService.getTenantDetails(this.editForm);
    if (tenantDetails.id !== null) {
      this.subscribeToSaveResponse(this.tenantDetailsService.update(tenantDetails));
    } else {
      this.subscribeToSaveResponse(this.tenantDetailsService.create(tenantDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITenantDetails>>): void {
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

  protected updateForm(tenantDetails: ITenantDetails): void {
    this.tenantDetails = tenantDetails;
    this.tenantDetailsFormService.resetForm(this.editForm, tenantDetails);
  }
}
