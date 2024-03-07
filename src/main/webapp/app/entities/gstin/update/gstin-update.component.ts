import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOrganization } from 'app/entities/organization/organization.model';
import { OrganizationService } from 'app/entities/organization/service/organization.service';
import { IGstin } from '../gstin.model';
import { GstinService } from '../service/gstin.service';
import { GstinFormService, GstinFormGroup } from './gstin-form.service';

@Component({
  standalone: true,
  selector: 'jhi-gstin-update',
  templateUrl: './gstin-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class GstinUpdateComponent implements OnInit {
  isSaving = false;
  gstin: IGstin | null = null;

  organizationsSharedCollection: IOrganization[] = [];

  editForm: GstinFormGroup = this.gstinFormService.createGstinFormGroup();

  constructor(
    protected gstinService: GstinService,
    protected gstinFormService: GstinFormService,
    protected organizationService: OrganizationService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareOrganization = (o1: IOrganization | null, o2: IOrganization | null): boolean =>
    this.organizationService.compareOrganization(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gstin }) => {
      this.gstin = gstin;
      if (gstin) {
        this.updateForm(gstin);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gstin = this.gstinFormService.getGstin(this.editForm);
    if (gstin.id !== null) {
      this.subscribeToSaveResponse(this.gstinService.update(gstin));
    } else {
      this.subscribeToSaveResponse(this.gstinService.create(gstin));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGstin>>): void {
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

  protected updateForm(gstin: IGstin): void {
    this.gstin = gstin;
    this.gstinFormService.resetForm(this.editForm, gstin);

    this.organizationsSharedCollection = this.organizationService.addOrganizationToCollectionIfMissing<IOrganization>(
      this.organizationsSharedCollection,
      gstin.organization,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.organizationService
      .query()
      .pipe(map((res: HttpResponse<IOrganization[]>) => res.body ?? []))
      .pipe(
        map((organizations: IOrganization[]) =>
          this.organizationService.addOrganizationToCollectionIfMissing<IOrganization>(organizations, this.gstin?.organization),
        ),
      )
      .subscribe((organizations: IOrganization[]) => (this.organizationsSharedCollection = organizations));
  }
}
