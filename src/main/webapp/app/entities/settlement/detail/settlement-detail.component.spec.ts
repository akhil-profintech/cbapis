import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SettlementDetailComponent } from './settlement-detail.component';

describe('Settlement Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SettlementDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: SettlementDetailComponent,
              resolve: { settlement: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SettlementDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load settlement on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SettlementDetailComponent);

      // THEN
      expect(instance.settlement).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
